/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, { enumerable: true, get: getter });
/******/ 		}
/******/ 	};
/******/
/******/ 	// define __esModule on exports
/******/ 	__webpack_require__.r = function(exports) {
/******/ 		if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 			Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 		}
/******/ 		Object.defineProperty(exports, '__esModule', { value: true });
/******/ 	};
/******/
/******/ 	// create a fake namespace object
/******/ 	// mode & 1: value is a module id, require it
/******/ 	// mode & 2: merge all properties of value into the ns
/******/ 	// mode & 4: return value when already ns object
/******/ 	// mode & 8|1: behave like require
/******/ 	__webpack_require__.t = function(value, mode) {
/******/ 		if(mode & 1) value = __webpack_require__(value);
/******/ 		if(mode & 8) return value;
/******/ 		if((mode & 4) && typeof value === 'object' && value && value.__esModule) return value;
/******/ 		var ns = Object.create(null);
/******/ 		__webpack_require__.r(ns);
/******/ 		Object.defineProperty(ns, 'default', { enumerable: true, value: value });
/******/ 		if(mode & 2 && typeof value != 'string') for(var key in value) __webpack_require__.d(ns, key, function(key) { return value[key]; }.bind(null, key));
/******/ 		return ns;
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 0);
/******/ })
/************************************************************************/
/******/ ({

/***/ "./app/routes/main/main.controller.js":
/*!********************************************!*\
  !*** ./app/routes/main/main.controller.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

eval("//@ts-check\r\n/**\r\n * Main Route Contoller\r\n * @param {object} router\r\n */\r\nmodule.exports = (router) => {\r\n    router.get(\"/\",\r\n        /**\r\n         * @param {object} req\r\n         * @param {object} res\r\n         */\r\n        (req, res) => {\r\n            const data = {\r\n            };\r\n            req.vueOptions.head.title = \"OpenFlow Client\";\r\n            req.vueOptions.head.scripts.push(\r\n                { src: \"https://unpkg.com/axios/dist/axios.min.js\"},\r\n                { src: \"https://unpkg.com/tail@0.2.0/tail.js\"},\r\n                );\r\n            res.renderVue(\"main/main.vue\", data, req.vueOptions);\r\n        },\r\n    );\r\n\r\n    router.post(\"/\",\r\n    /**\r\n     * @param {object} req\r\n     * @param {object} res\r\n     */\r\n    (req, res) => {\r\n        const data = {\r\n            message: \"POST\",\r\n            body: req.body,\r\n        };\r\n\r\n        const fs = __webpack_require__(!(function webpackMissingModule() { var e = new Error(\"Cannot find module 'fs'\"); e.code = 'MODULE_NOT_FOUND'; throw e; }()));\r\n        fs.appendFile(\"./temp.txt\", JSON.stringify(req.body) + \"\\n\", function(err) {\r\n            if(err) {\r\n                return console.log(err);\r\n            }\r\n\r\n            console.log(\"The file was saved!\");\r\n        }); \r\n        \r\n        //vue.data.messages = vue.data.messages + \"\\n\" + req.body;\r\n\r\n        res.json(data);\r\n    },\r\n);\r\n};\r\n\n\n//# sourceURL=webpack:///./app/routes/main/main.controller.js?");

/***/ }),

/***/ 0:
/*!**************************************************!*\
  !*** multi ./app/routes/main/main.controller.js ***!
  \**************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

eval("module.exports = __webpack_require__(/*! ./app/routes/main/main.controller.js */\"./app/routes/main/main.controller.js\");\n\n\n//# sourceURL=webpack:///multi_./app/routes/main/main.controller.js?");

/***/ })

/******/ });