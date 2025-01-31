import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'story_editor_method_channel.dart';

abstract class StoryEditorPlatform extends PlatformInterface {
  /// Constructs a StoryEditorPlatform.
  StoryEditorPlatform() : super(token: _token);

  static final Object _token = Object();

  static StoryEditorPlatform _instance = MethodChannelStoryEditor();

  /// The default instance of [StoryEditorPlatform] to use.
  ///
  /// Defaults to [MethodChannelStoryEditor].
  static StoryEditorPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [StoryEditorPlatform] when
  /// they register themselves.
  static set instance(StoryEditorPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> openStoryEditor() {
    throw UnimplementedError('Story Editor has not been implemented.');
  }
}
