package example.com.flutterapp.plugin

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry

class TextViewPlugin : FlutterPlugin{

    fun TextViewPlugin(){
    }

    fun registerWith(registrar: PluginRegistry.Registrar){
        registrar.platformViewRegistry().registerViewFactory("example.textview.com",TextViewFactory(registrar.messenger(),registrar.view(),null))
    }

    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        binding.platformViewRegistry.registerViewFactory("example.textview.com",TextViewFactory(binding.binaryMessenger,null,null))
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {

    }


}