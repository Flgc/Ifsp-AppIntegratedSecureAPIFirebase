require('dotenv').config();
const express = require('express');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const cors = require('cors');

const app = express();
app.use(express.json());
app.use(cors());

const PORT = process.env.PORT || 3000;
const SECRET = process.env.JWT_SECRET || 'secureapp_super_secret_key_2024';

// Dados em memória (simulando banco de dados)
const users = [
    {
        id: '1',
        email: 'user@example.com',
        password: bcrypt.hashSync('123456', 8),
        name: 'Usuário Teste'
    }
];

let items = [
    {
        id: '1',
        name: 'Item 1',
        description: 'Descrição do item 1',
        createdAt: new Date().toISOString()
    },
    {
        id: '2',
        name: 'Item 2',
        description: 'Descrição do item 2',
        createdAt: new Date().toISOString()
    }
];

// Middleware de autenticação
function authMiddleware(req, res, next) {
    const authHeader = req.headers.authorization;

    if (!authHeader) {
        return res.status(401).json({ error: 'Token não fornecido' });
    }

    const token = authHeader.split(' ')[1];

    try {
        const decoded = jwt.verify(token, SECRET);
        req.userId = decoded.userId;
        next();
    } catch (err) {
        return res.status(401).json({ error: 'Token inválido ou expirado' });
    }
}

// Rota de login
app.post('/auth/login', (req, res) => {
    const { email, password } = req.body;

    if (!email || !password) {
        return res.status(400).json({ error: 'Email e senha são obrigatórios' });
    }

    const user = users.find(u => u.email === email);

    if (!user) {
        return res.status(401).json({ error: 'Usuário não encontrado' });
    }

    const passwordValid = bcrypt.compareSync(password, user.password);

    if (!passwordValid) {
        return res.status(401).json({ error: 'Senha inválida' });
    }

    const token = jwt.sign(
        { userId: user.id, email: user.email },
        SECRET,
        { expiresIn: '1h' }
    );

    res.json({
        token,
        userId: user.id,
        name: user.name
    });
});

// Rotas de itens (protegidas)
app.get('/items', authMiddleware, (req, res) => {
    res.json(items);
});

app.post('/items', authMiddleware, (req, res) => {
    const { name, description } = req.body;

    if (!name || !description) {
        return res.status(400).json({ error: 'Nome e descrição são obrigatórios' });
    }

    const newItem = {
        id: Date.now().toString(),
        name,
        description,
        createdAt: new Date().toISOString()
    };

    items.push(newItem);
    res.status(201).json(newItem);
});

app.put('/items/:id', authMiddleware, (req, res) => {
    const { id } = req.params;
    const { name, description } = req.body;

    const itemIndex = items.findIndex(item => item.id === id);

    if (itemIndex === -1) {
        return res.status(404).json({ error: 'Item não encontrado' });
    }

    if (!name || !description) {
        return res.status(400).json({ error: 'Nome e descrição são obrigatórios' });
    }

    items[itemIndex] = {
        ...items[itemIndex],
        name,
        description
    };

    res.json(items[itemIndex]);
});

app.delete('/items/:id', authMiddleware, (req, res) => {
    const { id } = req.params;

    const itemIndex = items.findIndex(item => item.id === id);

    if (itemIndex === -1) {
        return res.status(404).json({ error: 'Item não encontrado' });
    }

    items = items.filter(item => item.id !== id);
    res.status(204).send();
});

app.listen(PORT, () => {
    console.log(`🚀 Backend rodando na porta ${PORT}`);
    console.log(`🔐 Ambiente: ${process.env.NODE_ENV || 'development'}`);
    console.log(`📝 Credenciais de teste: user@example.com / 123456`);
});