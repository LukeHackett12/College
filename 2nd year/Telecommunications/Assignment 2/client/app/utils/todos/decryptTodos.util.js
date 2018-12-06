// @ts-check
const jwt = require("jsonwebtoken");
const {TodoModel} = require("../../models");

/**
 * Decrypts Todos from hash
 * @param {string} token
 * @returns {TodoModel[]}
 */
function decryptTodo(token) {
    /** @type {TodoModel[]} */
    let todoArray = [];
    try {
        const decoded = jwt.verify(token, "shhhhh");
        // @ts-ignore
        if (decoded && typeof decoded === "object" && decoded.data) {
            // @ts-ignore
            const data = decoded.data;
            if (data && Array.isArray(data)) {
                data.forEach(element => {
                    todoArray.push(new TodoModel(element));
                });
            }
        } else {
            throw new Error("bad decode");
        }
        return todoArray;
    } catch (error) {
        throw error;
    }
}

module.exports = decryptTodo;
