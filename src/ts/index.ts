import * as cordova from 'cordova';

interface FileHandler {
  (files: ArrayBuffer): void;
}

const PLUGIN_NAME = 'ROILAssociateFileType';

class ROILAssociateFileType {
  setFileHandler(handler: FileHandler) {
    cordova.exec(
      file => handler(file),
      err => console.log(err),
      PLUGIN_NAME,
      'setFileHandler'
    );
  }
}

const instance = new ROILAssociateFileType();
export { instance as ROILAssociateFileType };
