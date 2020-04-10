(ns sketch.p5)

(defmacro defsketch [name methods-spec]
  (let [parent-id (str name)]
    `(do (ensure-parent ~parent-id)
         (def ~name (instance ~methods-spec ~parent-id)))))
