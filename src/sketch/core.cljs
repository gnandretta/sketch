(ns sketch.core
  (:require [sketch.p5 :as p5]))

(p5/defsketch example
  (setup []
    (p5/create-canvas 640 480
  (draw [world]
    (p5/fill (if (:mouse-is-pressed world) 0 255))
    (p5/ellipse (:mouse-x world) (:mouse-y world) 80 80)))
