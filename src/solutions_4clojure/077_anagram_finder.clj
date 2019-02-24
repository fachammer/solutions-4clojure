(ns solutions-4clojure.077-anagram-finder)

(defn anagrams [coll]
  (->> coll
       (group-by frequencies)
       vals
       (filter #(> (count %) 1))
       (map set)
       set))
