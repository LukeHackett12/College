//@ts-check
const express = require("express");
const config = require("./config");
const Router = require("./router");
const app = express();

Router.init(app, config);

app.listen(config.port, () => {
    // tslint:disable-next-line:no-console
    console.log(`Worker ${process.pid} running a ${config.env} server listening on port ${config.port}`);
});
