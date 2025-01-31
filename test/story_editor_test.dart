import 'package:flutter_test/flutter_test.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';
import 'package:story_editor/story_editor.dart';
import 'package:story_editor/story_editor_method_channel.dart';
import 'package:story_editor/story_editor_platform_interface.dart';

class MockStoryEditorPlatform
    with MockPlatformInterfaceMixin
    implements StoryEditorPlatform {
  @override
  Future<String?> openStoryEditor() => Future.value('42');
}

void main() {
  final StoryEditorPlatform initialPlatform = StoryEditorPlatform.instance;

  test('$MethodChannelStoryEditor is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelStoryEditor>());
  });

  test('getPlatformVersion', () async {
    StoryEditor storyEditorPlugin = StoryEditor();
    MockStoryEditorPlatform fakePlatform = MockStoryEditorPlatform();
    StoryEditorPlatform.instance = fakePlatform;

    expect(await storyEditorPlugin.openStoryEditor(), '42');
  });
}
