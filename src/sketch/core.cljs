(ns sketch.core
  (:require [sketch.p5 :as p5]))

(p5/defsketch example
  (setup []
    (p5/create-canvas 640 480)
    {:colors ["#aaffe4" "#c991e1" "#ffe9aa"]})
  (update [state {:keys [frame-count]}]
    (update state :colors (fn [[current & others :as colors]]
                           (if (zero? (mod frame-count 60))
                             (conj (vec others) current)
                             colors))))
  (draw [{:keys [colors mouse-is-pressed mouse-x mouse-y]}]
    (p5/fill (if mouse-is-pressed 0 (first colors)))
    (p5/ellipse mouse-x mouse-y  80 80)))

(comment
  (.setup example))
