(ns solutions-4clojure.065-black-box-testing)

(defn seq-type [coll]
  (let [s (conj coll [:a :b] [:c :d] [:c :d] [:c :e])]
    (cond
      (= (concat [[:c :e] [:c :d] [:c :d] [:a :b]] coll) s) :list
      (= (concat coll [[:a :b] [:c :d] [:c :d] [:c :e]]) s) :vector
      (= (conj coll [:c :e] [:c :d] [:a :b]) s) :set
      :else :map)))
