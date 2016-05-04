Ext.define('Testpro.testpro.web.com.controller.appinsight.health.PCuiController', {
     extend: 'Testpro.view.fw.frameworkController.FrameworkViewController',
     alias: 'controller.PCuiController',
     onSaveClick: function(me, e, eOpts) {
          var componentArray = [this.view.down('#textfield_ext_10556'), this.view.down('#numberfield_ext_10588'), this.view.down('#textfield_ext_10852'), this.view.down('#numberfield_ext_10893')];
          var invalidCompoentArray = this.validateComponents(componentArray);
          if (invalidCompoentArray.length > 0) {
               return;
          }
          var jsonData = {};
          jsonData.pName = this.view.down('#textfield_ext_10556').getValue();
          jsonData.age = this.view.down('#numberfield_ext_10588').getValue();
          jsonData.child = {};
          jsonData.child.cName = this.view.down('#textfield_ext_10852').getValue();
          jsonData.child.cage = this.view.down('#numberfield_ext_10893').getValue();
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/Parent/',
               async: true,
               method: 'POST',
               sender: scope,
               jsonData: jsonData,
               me: me,
               maskEnable: true,
               maskEle: scope,
               success: function(response, scope) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         Ext.Msg.alert('Server Response', responseText.response.message);
                         var responseData = responseText.response.data;
                         var aa = scope.sender.down('#gridpanel_ext_11153');
                         aa.store.setData(responseData);
                    } else {
                         scope.sender.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    scope.sender.responseHandler(responseText);
               }
          }, scope);
     }
});