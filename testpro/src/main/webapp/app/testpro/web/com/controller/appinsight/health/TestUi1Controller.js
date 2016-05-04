Ext.define('Testpro.testpro.web.com.controller.appinsight.health.TestUi1Controller', {
     extend: 'Testpro.view.fw.frameworkController.FrameworkViewController',
     alias: 'controller.TestUi1Controller',
     onB1Click: function(me, e, eOpts) {
          var jsonData = {};
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/AddressType/',
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
     onB2Click: function(me, e, eOpts) {
          var jsonData = {};
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/ContactType/',
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
     onB3Click: function(me, e, eOpts) {
          var jsonData = {};
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/Gender/',
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
     }
});