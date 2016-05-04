Ext.define('Testpro.view.smartdevice.searchengine.ChartDetailView',{
	extend : 'Ext.panel.Panel',
	xtype: 'chart-detail-view',
	//layout:{type:'vbox',align:'stretch',pack:'stretch'},
	layout:'fit',
	bodyPadding:10,
	itemId:'chart-detail-view',
	title:'',
	chartJson:undefined,
	selectedItemCls:'dataViewSelected',
	header : {
		titlePosition : 1,
		items : [{ 
               xtype: 'button',
               text: '< Back',
               scale:'medium',
              // icon:'resources/appicons/ic_drawer_collapse.png',
               margin:'0 5 0 0',
               listeners:{
                  click:function(){
                	  var chartPanel = this.up('#chartPanel');
                	  var chartDetailPanel = this.up('#chart-detail-view');
					  chartPanel.setActiveItem(chartDetailPanel.prev());
					  chartDetailPanel.chartJson = undefined;
                  }
               }
   }]
	},
	listeners:{
		
		 show :function(chartdetailview){
			 if(this.chartJson){
				 var fusionchart = new FusionCharts(this.chartJson);
				 fusionchart.width = '100%';
				 fusionchart.height ='100%';
				 fusionchart.render(this.body.id);
			 }
		 }
	}
	

});