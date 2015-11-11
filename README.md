# ShowInstalledApp
Demo app display all installed app in your device

This is include: 
  1 activity (MainActiviy) to cotain ListView 
  1 adapter (ListAdapter) to custom list view
  1 asyncTask (LoadTask) to get list of installed app and update UI listview
  
Reference:
  PackageManager(pm) : manager all package in your device
  ApplicationInfo(info) : object contain a info app
  
Some method:
   pm.getInstalledApplications(PackageManager.GET_META_DATA) : get list of app in your device
   info.loadLabel(pm) : get name of app return string 
   info.loadIcon(pm) : get Icon of app return Drawble
   
