(ns sketch.core
  (:require [sketch.p5 :as p5]))

(p5/defsketch example
  (setup []
    (p5/create-canvas 640 480))
  (draw [p]
    (p5/fill (if (.-mouseIsPressed p) 0 255))
    (p5/ellipse (.-mouseX p) (.-mouseY p) 80 80)))
