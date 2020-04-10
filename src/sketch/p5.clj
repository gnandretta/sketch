(ns sketch.p5)

(defn- method-form->method-spec [[name & args-and-body]]
  [(str name) (conj args-and-body 'clojure.core/fn)])

(defmacro defsketch [name & method-forms]
  (let [parent-id (str name)
        methods-spec (mapv method-form->method-spec method-forms)]
    `(do (ensure-parent ~parent-id)
         (def ~name (instance ~methods-spec ~parent-id)))))
