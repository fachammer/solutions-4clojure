(ns solutions-4clojure.023-reverse-a-sequence)

(fn reverse [coll]
  (reduce conj '() coll))
