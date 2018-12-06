//@ts-check
const express = require("express");
const glob = require("glob");
const logger = require("morgan");
const cookieParser = require("cookie-parser");
const bodyParser = require("body-parser");
const compress = require("compression");
const methodOverride = require("method-override");
const cookieSession = require("cookie-session");
const helmet = require("helmet");
const validator = require("express-validator");
const expressVue = require("express-vue");
const path = require("path");

/**
 *
 * @param {object} app
 * @param {object} config
 */
module.exports.init = (app, config) => {
    //Setup
    const env = process.env.NODE_ENV || "development";
    const router = express.Router();
    let logType = "dev";
    app.locals.ENV = env;
    app.locals.ENV_DEVELOPMENT = (env === "development");
    app.locals.rootPath = process.env.ROOT_PATH;

    //ExpressVue Setup
    const vueOptions = {
        rootPath: path.join(__dirname, "routes"),
        head: {
            styles: [{ style: "assets/rendered/style.css" }]
        },
    };

    // @ts-ignore
    const expressVueMiddleware = expressVue.init(vueOptions);
    app.use(expressVueMiddleware);

    //Security
    app.use(helmet());
    app.disable("x-powered-by");

    app.use(bodyParser.json());
    app.use(bodyParser.urlencoded({
        extended: true,
    }));
    app.use(validator());

    app.use(compress());

    app.use(app.locals.rootPath, express.static(config.root));

    let sessionConfig = {
        name: "session",
        keys: [
            "CHANGE_ME",
        ],
        resave: true,
        saveUninitialized: true,
        cookie: {
            domain: "foo.bar.com",
            secure: false,
            httpOnly: true,
        },
    };
    if (env === "production") {
        app.set("trust proxy", 1);
        sessionConfig.cookie.secure = true;
        logType = "combined";
    }

    if (env === "development") {
        app.use(logger(logType));
    }

    app.use(cookieParser());

    app.use(methodOverride());

    app.use(cookieSession(sessionConfig));

    app.use("/", router);

    let controllers = glob.sync(config.root + "/routes/**/*.js");
    controllers.forEach(function(controller) {
        module.require(controller)(router);
    });

    /**
     * Generic 404 handler
     * @param {object} req
     * @param {object} res
     */
    function error404handler(req, res) {
        const data = {
            title: "Error 404",
        };
        req.vueOptions = {
            head: {
                title: "Error 404",
            },
        };
        res.statusCode = 404;
        res.renderVue("error.vue", data, req.vueOptions);
    }
    app.use(error404handler);

    /**
     * Generic Error handling route
     * @param {object} error
     * @param {object} req
     * @param {object} res
     * @param {Function} next
     */
    function genericErrorHandler(error, req, res, next) {
        res.statusCode = 500;
        let data = {
            debug: env === "development",
            errorCode: error.code,
            error: error.stack,
        };
        if (res.statusCode) {
            res.renderVue("error.vue", data);
        } else {
            next();
        }
    }
    app.use(genericErrorHandler);
};
