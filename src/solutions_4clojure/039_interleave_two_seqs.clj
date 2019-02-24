(ns solutions-4clojure.039-interleave-two-seqs)

(defn my-interleave [collA collB]
  (apply concat (map (fn [a b] [a b]) collA collB)))
