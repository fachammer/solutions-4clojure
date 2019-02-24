(ns solutions-4clojure.053-longest-increasing-sub-seq)

(defn longest-increasing-subsequence [coll]
  (let [inc-subsequences (reverse (filter
                                   (fn [coll] (every? (fn [[a b]] (< a b)) (partition 2 1 coll)))
                                   (for [start (range (count coll))
                                         end (range (inc (inc start)) (inc (count coll)))]
                                     (subvec coll start end))))]
    (if (empty? inc-subsequences)
      []
      (apply max-key count inc-subsequences))))
