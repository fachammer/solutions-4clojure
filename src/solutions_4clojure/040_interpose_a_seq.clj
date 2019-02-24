(ns solutions-4clojure.040-interpose-a-seq)

(defn my-interpose [sep coll]
  (take (dec (* 2 (count coll))) (interleave coll (repeat sep))))
