(ns sketch.core
  (:require [sketch.p5 :as p5]))

(p5/defsketch example
  (setup []
    (p5/create-canvas 640 480))
  (update [state {:keys [frame-count]}]
    (update state :color (fn [color]
                           (if (zero? (mod frame-count 60))
                             (rand-nth ["#aaffe4" "#c991e1" "#ffe9aa"])
                             (or color "#aaffe4")))))
  (draw [{:keys [color mouse-is-pressed mouse-x mouse-y]}]
    (p5/fill (if mouse-is-pressed 0 color))
    (p5/ellipse mouse-x mouse-y  80 80)))
