import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:story_editor/story_editor.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  final _storyEditorPlugin = StoryEditor();

  @override
  void initState() {
    super.initState();
  }

  Future<void> initPlatformState() async {
    String storyMedia;

    try {
      storyMedia = await _storyEditorPlugin.openStoryEditor() ??
          'Unknown platform version';
    } on PlatformException {
      storyMedia = 'Failed to get platform version.';
    }

    if (!mounted) return;

    setState(() {
      _platformVersion = storyMedia;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: GestureDetector(
              onTap: () {
                initPlatformState();
              },
              child: Text('Running on: $_platformVersion\n')),
        ),
      ),
    );
  }
}
