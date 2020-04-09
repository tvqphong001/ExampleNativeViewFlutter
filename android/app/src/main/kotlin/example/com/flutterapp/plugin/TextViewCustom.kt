package example.com.flutterapp.plugin

import android.content.Context
import android.view.View
import android.widget.TextView
import example.com.flutterapp.R
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView


class TextViewCustom (context: Context, message : BinaryMessenger, id : Int, containerView: View?) : PlatformView, MethodChannel.MethodCallHandler {
    private var methodChannel : MethodChannel =  MethodChannel(message, "example.textview.com$id")
    private var mView : View?=null
    init {
        if(mView==null){
            mView = View.inflate(context, R.layout.test_text_view,null)
            methodChannel.setMethodCallHandler(this)
        }
    }
    override fun getView(): View {
        return mView!!
    }

    override fun dispose() {
        methodChannel.setMethodCallHandler(null)
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when(call.method){
            "changeText" -> {
                val list = call.arguments as List<*>
                val data = list[0] as String
                mView!!.findViewById<TextView>(R.id.textView).text = data
                result.success(true)
            }
            else -> {
                result.success(true)
            }
        }
    }

}