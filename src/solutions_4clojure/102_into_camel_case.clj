(ns solutions-4clojure.102-into-camel-case)

(defn hyphen-case->camel-case [s]
  (let [[first-word & rest-words] (clojure.string/split s #"-")]
    (apply str first-word (map clojure.string/capitalize rest-words))))
