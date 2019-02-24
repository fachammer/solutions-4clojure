(ns solutions-4clojure.158-decurry)

(fn decurry [f]
  (fn [& args]
    (if (and (seq args) (fn? f))
      (apply (decurry (f (first args))) (rest args))
      f)))
