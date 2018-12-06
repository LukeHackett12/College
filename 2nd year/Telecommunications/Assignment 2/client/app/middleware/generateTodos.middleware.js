// @ts-check
const {TodoModel} = require("../models");
/**
 * Generates Todo's if empty
 * @param {object} req
 * @param {object} res
 * @param {object} next
 */
function generateTodosMiddleware(req, res, next) {
    if (!req.session.todos) {
        let todos = [
            "Eat Food",
            "Sleep",
            "Drink Coffee",
            "Write Code",
        ];
        req.session.todos = [];
        todos.forEach((element, key) => {
            let todo = new TodoModel({
                id: key,
                title: element,
                completed: false,
            });
            req.session.todos.push(todo);
        });
    }
    next();
}

module.exports = generateTodosMiddleware;
