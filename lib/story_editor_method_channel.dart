import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'story_editor_platform_interface.dart';

/// An implementation of [StoryEditorPlatform] that uses method channels.
class MethodChannelStoryEditor extends StoryEditorPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('story_editor');

  @override
  Future<String?> openStoryEditor() async {
    final version = await methodChannel.invokeMethod<String>('openStoryEditor');
    return version;
  }
}
