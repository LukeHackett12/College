<template>
    <div>
        <section class="todoapp">
            <header class="header">
                <h1>{{title}}</h1>
                <input class="new-todo"
                autofocus autocomplete="off"
                placeholder="What needs to be done?"
                v-model="newTodo"
                @keyup.enter="addTodo">
            </header>
            <section class="main" v-show="todos.length">
                <input class="toggle-all" type="checkbox" v-model="allDone">
                <ul class="todo-list">
                    <li v-for="todo in todos"
                        class="todo"
                        :key="todo.id"
                        :class="{ completed: todo.completed, editing: todo == editedTodo }">
                        <div class="view">
                            <input class="toggle" type="checkbox" v-model="todo.completed">
                            <label>{{ todo.title }}</label>
                            <button class="destroy" @click="removeTodo(todo)"></button>
                        </div>
                        <input class="edit" type="text"
                            v-model="todo.title"
                            v-todo-focus="todo == editedTodo"
                            @blur="doneEdit(todo)"
                            @keyup.enter="doneEdit(todo)"
                            @keyup.esc="cancelEdit(todo)">
                    </li>
                </ul>
            </section>
            <footer class="footer" v-show="showFooter">
                <span class="todo-count">
                    <strong>{{ remaining }}</strong> {{ remaining | pluralize }} left
                </span>
                <ul class="filters">
                    <li><a href="todo" :class="{ selected: visibility == 'all' }">All</a></li>
                    <li><a href="?filter=active" :class="{ selected: visibility == 'active' }">Active</a></li>
                    <li><a href="?filter=completed" :class="{ selected: visibility == 'completed' }">Completed</a></li>
                </ul>
                <button class="clear-completed" @click="removeCompleted" v-show="todos.length > remaining">
                    Clear completed
                </button>
            </footer>
        </section>
        <button @click="shareTodos()">Share</button>
    </div>
</template>

<script>
export default {
    data: function () {
        return {
            title: ''
        }
    },
    computed: {
        remaining: function () {
            let remaining = 0;
            this.todos.forEach(element => {
                if (!element.completed) {
                    remaining++;
                }
            });
            return remaining;
        },
        allDone: {
            get: function () {
                return this.remaining === 0
            },
            set: function (value) {
                this.todos.forEach(function (todo) {
                    todo.completed = value
                })
            }
        }
    },
    filters: {
        pluralize: function (n) {
            return n === 1 ? 'item' : 'items'
        }
    },
    methods: {
        addTodo: function () {
            var value = this.newTodo && this.newTodo.trim()
            if (!value) {
                return
            }
            this.todos.push({
                id: this.todos.length+1,
                title: value,
                completed: false
            })
            this.newTodo = ''
        },
        removeTodo: function (todo) {
            this.todos.splice(this.todos.indexOf(todo), 1)
        },
        doneEdit: function (todo) {
            if (!this.editedTodo) {
                return
            }
            this.editedTodo = null
            todo.title = todo.title.trim()
            if (!todo.title) {
                this.removeTodo(todo)
            }
        },
        cancelEdit: function (todo) {
            this.editedTodo = null
            todo.title = this.beforeEditCache
        },
        removeCompleted: function () {
            this.todos = this.todos.filter(function(todo) {
                return !todo.completed;
            })
        },
        shareTodos: function() {
            axios.post("/todo/share", this.todos)
                .then(function(response) {
                    window.location = response.data.link;
                });
        }
    },
    directives: {
        'todo-focus': function (el, binding) {
            if (binding.value) {
                el.focus()
            }
        }
    },
    watch: {
        todos: {
            handler: function(todos) {
                this.showFooter = todos.length > 0;
                axios.post(`/todo/update`, todos);
            },
            deep: true
        }
    }
}
</script>
