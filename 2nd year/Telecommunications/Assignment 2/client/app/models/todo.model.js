// @ts-check

/**
 * @typedef TodoType
 * @prop {number} id
 * @prop {string} title
 * @prop {boolean} completed
 */

class TodoModel {
    /**
     * @constructor
     * @param {TodoType} todo
     * @prop {number} id
     * @prop {string} title
     * @prop {boolean} [completed]
     */
    constructor(todo) {
        this.id = todo.id;
        this.title = todo.title;
        this.completed = todo.completed || false;
    }
}

module.exports = TodoModel;
