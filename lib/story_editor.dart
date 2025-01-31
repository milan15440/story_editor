import 'story_editor_platform_interface.dart';

class StoryEditor {
  Future<String?> openStoryEditor() {
    return StoryEditorPlatform.instance.openStoryEditor();
  }
}
