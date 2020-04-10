(ns sketch.core)

(defn setup []
  (js/createCanvas 640 480))

(defn draw []
  (js/fill (if js/mouseIsPressed 0 255))
  (js/ellipse js/mouseX js/mouseY 80 80))

(set! (.-setup js/window) setup)
(set! (.-draw js/window) draw)
