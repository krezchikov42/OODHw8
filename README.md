#SimpleAnimator
Jack Mastrangelo and Daniel Goldstein

#Views
##Text
The verbose view outputs fairly typically. It is implemented mostly
by visitors to the AnimationObjects and Commands that render them in
the proper text way.

##SVG
The SVG and SVGLoopback views are implemented very similarly
to the Verbose view, with visitors visiting our AnimationObjects and
Commands.

##Visual
Our visual view is implemented mostly through the AnimationPanel
class that extends JPanel. Our actual view then just has to extend
JFrame and include that JPanel. Every time our controller advances a
tick the model sends back a list of ShapeAttributes. A
ShapeAttribute is a class that holds only the information necessary
to render a shape onto the screen.

##Interactive
The Interactive view still retains the AnimationPanel as the center
of the drawing, making it fairly easy to wrap buttons and checkboxes
around the animations. Users have the abiltity to play, pause, select
whether or not the animation loops, and change the speed. In order to
select shapes they simply tick checkboxes. If they want to export
to svg they hit the button and it exports either to stdout or an output
file if its been specified by -o in the command line arguments.