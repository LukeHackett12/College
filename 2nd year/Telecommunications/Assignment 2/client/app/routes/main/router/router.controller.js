//@ts-check

/**
 * Post Route Controller
 * @param {object} router
 */
module.exports = (router) => {
    router.get("/router",
        /**
         * @param {object} req
         * @param {object} res
         */
        (req, res) => {
            const data = {
                message: "POST",
            };
            req.vueOptions.head.title = "Send Message";
            req.vueOptions.head.scripts.push({
                src: "https://unpkg.com/axios/dist/axios.min.js",
            });
            
            res.renderVue("router/router.vue", data, req.vueOptions);
        },
    );

    router.post("/router",
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
