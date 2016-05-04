Ext.define('Testpro.testpro.web.com.controller.appinsight.health.OneuiController', {
     extend: 'Testpro.view.fw.frameworkController.FrameworkViewController',
     alias: 'controller.OneuiController',
     onSaveClick: function(me, e, eOpts) {
          var componentArray = [this.view.down('#textfield_ext_9966'), this.view.down('#numberfield_ext_9977')];
          var invalidCompoentArray = this.validateComponents(componentArray);
          if (invalidCompoentArray.length > 0) {
               return;
          }
          var jsonData = {};
          jsonData.tnm = this.view.down('#textfield_ext_9966').getValue();
          jsonData.tno = this.view.down('#numberfield_ext_9977').getValue();
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/TestOne/',
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
                    } else {
                         scope.sender.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    scope.sender.responseHandler(responseText);
               }
          }, scope);
     },
     onResetClick: function(me, e, eOpts) {
          this.view.down('#textfield_ext_9966').reset();
          this.view.down('#numberfield_ext_9977').reset();
     }
});