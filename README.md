# Chrome Dino </br>
### [Watch Trailer](https://www.youtube.com/watch?v=9khZpnRMiHk)
### [Recorded Demo](https://youtu.be/dPIWv86ULjM)
### [Scrum Meeting](Link coming soon)
### [Link to our work video after scrums](https://drive.google.com/file/d/1Mfdq_JS-9BlWed0IoxLoIsUygwCDtalj/view?usp=sharing)

## Team Name
Water

## Team Members

* [Adarsh Patil](https://github.com/adpatil036)
* [Anujot Singh](https://github.com/tojuna)
* [Rohit K Philip](https://github.com/rohitkphilip)
* [Yash Modi](https://github.com/yashm28sjsu)

## How to run the game
* Install AWT, Swing
* Clone this [repository](https://github.com/nguyensjsu/fa21-202-water)
* Run `GameWindow` inside the userinterface folder


## About Game
Guide the Tyrannosaurus rex across a side-scrolling landscape, avoiding obstacles to achieve a higher score. You can now change your character! Play as Tyrannosaurus rex, Bruce Lee or Mario!

![main-character1](https://user-images.githubusercontent.com/67829172/144568764-0f28c26a-79da-480f-b12a-80c1df91849e.png)
&nbsp;
![main-character6](https://user-images.githubusercontent.com/67829172/144568805-ec039e6e-25e9-4b81-adf0-ca724585e7af.png)
&nbsp;
![mario-3](https://user-images.githubusercontent.com/67829172/144722625-d96079e7-0f9c-4136-b8a4-0e13dcde90f0.png)

## [UI Wireframes](./images/ui-wireframes.png)

## Game Screenshots
#### Main Menu
<img width="599" alt="screenshot-1" src="https://user-images.githubusercontent.com/89710394/144723847-c029eb43-7d98-44a7-ab89-5803efcaf2e3.png">

#### Game Play
<img width="599" alt="screenshot-2" src="https://user-images.githubusercontent.com/89710394/144723849-f2799bf7-478d-423b-a379-e8dd783125e6.png">

#### Game End
<img width="603" alt="screenshot-3" src="https://user-images.githubusercontent.com/89710394/144723850-4dfa6622-b17b-4be6-a124-8a93953a5e17.png">



### [Project Dashboard](https://github.com/nguyensjsu/fa21-202-water/projects/1)
We tracked issues in project dashboard as:
 * TO-DO
 * IN PROGRESS
 * DONE
<img width="1137" alt="Screen Shot 2021-12-03 at 3 01 03 AM" src="https://user-images.githubusercontent.com/67829172/144592013-4862f052-5666-4fe9-90e0-0a7731eaba73.png">


### [Burndown Chart](https://docs.google.com/spreadsheets/d/1SoNHd6VdUsYvqtHj7YMbvN_78-oVNKeAV7NsBcfKB68/edit?usp=sharing)

* Task Sheet
  
![Task Sheet](./images/TaskSheet.png)

* Burndown Chart
  
![Burndown Chart](./images/BurndownTeamWater.png) 

### [Retrospective Dashboard](./images/retrospective-dashboard.png)
<img width="744" alt="retrospective-dashboard" src="https://user-images.githubusercontent.com/89710394/144722812-c28c4597-863c-4549-a6ad-e021e3e35a6a.png">


## Design Notes

### State Pattern

<img width="1095" alt="Screen Shot 2021-12-04 at 11 40 19 AM" src="https://user-images.githubusercontent.com/67829172/144722594-e65b6252-16ed-4dc7-968d-be93d295b54b.png">

-   The state pattern is a behavioral design pattern. According to GoF definition, a state allows an object to alter its behavior when its internal state changes. The object will appear to change its class. State Pattern been used to transition between the different game playing states. The different states are: GamePlayingState, GameOverState, GameStartState and CharacterSelectionState

### [Decorator](./images/decorator_pattern_class_diagram.png)

![decorator_pattern_class_diagram](https://user-images.githubusercontent.com/89710394/144723089-d79de368-1278-4fcf-8d69-df2533dcc047.png)

-   Decorator pattern has been implemented for character selection.


### Singleton

<img width="1095" alt="Screen Shot 2021-12-04 at 11 40 19 AM" src="./images/singleton.png">

-   Singleton Pattern is used in settings screen.

### Abstract Factory

<img width="1095" alt="Screen Shot 2021-12-04 at 11 40 19 AM" src="./images/ClassDiagramAbstractFactory.jpg">

-   Abstract Factory Pattern is used to fetch objects of same kind at runtime for different choices of character.

### Individual Contributions
* Anujot - State Pattern, User Interface, Graphic Images Creation, Documentation, Demo Video, Agile Video
* Adarsh - Singleton Pattern, Animation Logic, Background Sound, User Story Video
* Rohit - Decorator Pattern, Graphic Images Creation, Util, Character Selection
* Yash -  Abstract Factory Pattern, Diagrams, Connecting components, Characters and Objects

### Team Journals
* [Anujot](https://docs.google.com/document/d/1OtRYFB6AZ2-ywfoYEYmuGZvEknHGLNkGpVMCrY4hjX8/edit)
* [Adarsh](https://docs.google.com/document/d/1QaUme6EvhNVO5APSkX10locUypCL7Xs5x-AKutCLCR4/edit?usp=sharing)
* [Rohit](./journals/rohit-team-project-journal.md)
* [Yash](https://docs.google.com/document/d/19b-PiPMiilFd61TAujqmiiGorWQeAdAyjc6aWBsXgiU/edit)
