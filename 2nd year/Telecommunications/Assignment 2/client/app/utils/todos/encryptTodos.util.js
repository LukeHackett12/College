// @ts-check
const jwt = require("jsonwebtoken");
const {TodoModel} = require("../../models");

/**
 * Decrypts Todos from hash
 * @param {TodoModel[]} todos
 * @returns {string}
 */
function encryptTodos(todos) {
    const data = {
        exp: Math.floor(Date.now() / 1000) + (60 * 60),
        data: todos,
    };
    const token = jwt.sign(data, "shhhhh");
    return token;
}

module.exports = encryptTodos;
