//@ts-check
/**
 * Main Route Contoller
 * @param {object} router
 */
module.exports = (router) => {
    router.get("/",
        /**
         * @param {object} req
         * @param {object} res
         */
        (req, res) => {
            const data = {
            };
            req.vueOptions.head.title = "OpenFlow Client";
            req.vueOptions.head.scripts.push(
                { src: "https://unpkg.com/axios/dist/axios.min.js"},
                );
            res.renderVue("main/main.vue", data, req.vueOptions);
        },
    );

    router.post("/",
    /**
     * @param {object} req
     * @param {object} res
     */
    (req, res) => {
        const data = {
            message: "POST",
            body: req.body,
        };

        const fs = require('fs');
        fs.appendFile("./temp.txt", JSON.stringify(req.body) + "\n", function(err) {
            if(err) {
                return console.log(err);
            }

            console.log("The file was saved!");
        }); 
        
        //vue.data.messages = vue.data.messages + "\n" + req.body;

        res.json(data);
    },
);
};
