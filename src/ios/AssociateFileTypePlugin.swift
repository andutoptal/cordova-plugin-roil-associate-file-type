import Foundation

@objc(AssociateFileTypePlugin) open class AssociateFileTypePlugin : CDVPlugin {
    var notification: Notification!
    var callbackId: String!

    @objc(setFileHandler:)
    func setFileHandler(_ command: CDVInvokedUrlCommand) -> Void {
        callbackId = command.callbackId;
    }

    open override func handleOpenURL(_ notification: Notification!) {
        self.notification = notification;

        let url: URL = notification!.object as! URL;

        let result: [UInt8];
        result = [UInt8]((try? Data(contentsOf: url))!);

        let response = CDVPluginResult.init(status: CDVCommandStatus.ok, messageAs: result);
        response?.setKeepCallbackAs(true);
        commandDelegate.send(response, callbackId: callbackId);
    }
}
