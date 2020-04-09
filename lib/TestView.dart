import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';


class TestViewController{
  final MethodChannel _channel;
  TestViewController._(int id): _channel = new MethodChannel('example.textview.com$id');

  Future<bool> onChangeText(String text)async{
     var data = await _channel.invokeMethod("changeText",[text]);
//     var data = await _channel.invokeMethod("changeText",text);
     return data;
  }
}

typedef void CreatedControllerCallback(TestViewController controller);

class TestView extends StatefulWidget {
  final CreatedControllerCallback onCreated;

  const TestView({Key key, this.onCreated}) : super(key: key);
  @override
  _TestViewState createState() => _TestViewState();
}

class _TestViewState extends State<TestView> {
  TestViewController controller;
  Widget view;

  void _onPlatformViewCreated(int id) {
//    if (widget.onCreated == null) {
//      return;
//    }
    if(controller == null){
      controller = new TestViewController._(id);
//      widget.onCreated(controller);
    }
  }

  @override
  Widget build(BuildContext context) {
    if(defaultTargetPlatform == TargetPlatform.android){
      // Android have new style code of create platform view
      // link https://flutter.dev/docs/development/packages-and-plugins/plugin-api-migration

      view = AndroidView(viewType: "example.textview.com",onPlatformViewCreated: _onPlatformViewCreated,);

    } else if(defaultTargetPlatform == TargetPlatform.iOS) {

      // because i don't have macbook so you can search gg to make it
      // link : https://medium.com/flutter-community/flutter-platformview-how-to-create-flutter-widgets-from-native-views-366e378115b6

      view =  Text(
          '$defaultTargetPlatform is not yet supported by the text_view plugin');

    } else {

      // that is fuchsia
      view =  Text(
          '$defaultTargetPlatform is not yet supported by the text_view plugin');

    }
    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text("title"),
      ),
      body: Center(
        child: Container(
          child: Column(
            children: <Widget>[
              Container(
                height:  200,
                width: MediaQuery.of(context).size.width,
                child: view,
              ),
             TextField(
               onChanged: (str){
                 controller.onChangeText(str);
               },
             )
            ],
          ),
        ),
      ),
    );
  }


}
