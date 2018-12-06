//@ts-check

/**
 * Post Route Controller
 * @param {object} router
 */
module.exports = (router) => {
    router.get("/receive",
        /**
         * @param {object} req
         * @param {object} res
         */
        (req, res) => {
            const data = {
                message: "GET",
            };
            req.vueOptions.head.title = "Receive Message";
            req.vueOptions.head.scripts.push({
                src: "https://unpkg.com/axios/dist/axios.min.js",
            });
            res.renderVue("receive/receive.vue", data, req.vueOptions);
        },
    );

    router.post("/receive",
        /**
         * @param {object} req
         * @param {object} res
         */
        (req, res) => {
            const data = {
                message: "POST",
                body: req.body,
            };
            res.json(data);
        },
    );
};
