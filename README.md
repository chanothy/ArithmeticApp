# Arithmetic App

Arithmetic app that has 3 difficulties, 4 operations, and choice to pick amount of questions. This app uses fragments to navigate between three pages and pass information between these three pages.

## Functionality 

The following **required** functionality is completed:

* [ ] User sees user input page to choose difficulty, operation, and number of questions
* [ ] User can click button to bring them to other fragments
* [ ] User can answer questions.
* [ ] User can view their ratio of questions answered correctly and restart.
* [ ] Uses safe args to move information between fragments.
* [ ] Has sound cue for correct and incorrect answers.
* [ ] Has toasts for correct and incorrect answers.
* [ ] Activity1 (FragmentUserInput) recieves data from Activity2 (FragmentMath) and displays message.

The following **extensions** are implemented:

* androidx.navigation.safeargs
* androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='appDemo' title='Video Walkthrough' width='50%' alt='Video Walkthrough' />
<img src='https://github.com/chanothy/ArithmeticApp/blob/project4/appDemoUpdated.gif' title='Video Walkthrough' width='50%' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

* One of the challenges was just understanding how the navhost worked for main activity and how fragments were set up. I also had to figure out how to transfer information from fragment to fragment through bundles. 
* A new issue I faced was figuring out how to have default values but still allowing other fragments to pass arguments and override the default values.

## License

    Copyright [2023] [Timothy Chan]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
