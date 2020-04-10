(ns sketch.core
  (:require [sketch.p5 :as p5]))

(p5/defsketch example
  (setup []
    (p5/create-canvas 640 480))
  (draw [{:keys [mouse-is-pressed mouse-x mouse-y]}]
    (p5/fill (if mouse-is-pressed 0 255))
    (p5/ellipse mouse-x mouse-y  80 80)))
