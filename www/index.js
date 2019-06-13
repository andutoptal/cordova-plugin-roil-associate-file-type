"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var cordova = require("cordova");
var PLUGIN_NAME = 'ROILAssociateFileType';
var ROILAssociateFileType = /** @class */ (function () {
    function ROILAssociateFileType() {
    }
    ROILAssociateFileType.prototype.setFileHandler = function (handler) {
        cordova.exec(function (file) { return handler(file); }, function (err) { return console.log(err); }, PLUGIN_NAME, 'setFileHandler');
    };
    return ROILAssociateFileType;
}());
var instance = new ROILAssociateFileType();
exports.ROILAssociateFileType = instance;
//# sourceMappingURL=index.js.map