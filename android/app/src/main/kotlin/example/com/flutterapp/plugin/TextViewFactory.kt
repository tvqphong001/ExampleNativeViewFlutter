package example.com.flutterapp.plugin

import android.content.Context
import android.view.View
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory


class TextViewFactory(messenger: BinaryMessenger, containerView: View?, createArgsCodec: MessageCodec<Any>?) : PlatformViewFactory(createArgsCodec) {
    private val messenger: BinaryMessenger = messenger
    private val containerView: View? = containerView
    override fun create(context: Context?, viewId: Int, args: Any?): PlatformView {
        return TextViewCustom(context!!,messenger,viewId,containerView)
    }
}