Ext.define('Testpro.view.smartdevice.searchengine.ChartListView',{
	extend : 'Ext.view.View',
	xtype: 'chart-list-view',
	itemId:'chart-list-view',
	chartJson : undefined,
	title:'Chart',
	iconAlign:'top',
	scrollable:true,
	loadingText:'Loading...',
	icon:'images/rpticon/chart.png',
	tpl:new Ext.XTemplate(
		    '<tpl for=".">',
		        '<div style="vertical-align:middle;box-shadow: 1px 1px 1px 1px #888888;background:#ffffff;padding:10px;height:50px;margin: 10px;font-size:14px;font-weight:bold;" class="thumb-wrap">',
		          '<img style="margin-right:20px;" height=18px width=18px src="images/rpticon/chart.png"/>{chartJson.chartTitle}',
		        '</div>',
		    '</tpl>'
		),
    itemSelector: 'div.thumb-wrap',
    emptyText: '<div align="center"><img src="resources/images/shared/icon-info.png"/><div align="center" style="align:middle;font-size:14px;font-weight:bold;color:#157fcc;">No result found!</div></div>',
	listeners :{
		  itemclick :function( dataview, record, item, index, e, eOpts ){
				var chartPanel = dataview.up('#chartPanel');
				var chartDetailView = dataview.next();
				chartDetailView['chartJson'] = record.data.chartJson;
				chartPanel.setActiveItem(chartDetailView);
				chartDetailView.setTitle(record.data.chartJson.chartTitle);
			 }
		  }
	
});