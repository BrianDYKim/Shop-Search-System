## Shop 검색용 서버

### Environment
- JDK: JDK11
- Language: Java
- Deployment: AWS Lambda
- DB: Elasticsearch
- Event Broker: Kafka

### Indexes
**shops**
~~~json
{
  "settings": {
    "number_of_shards": 5,
    "number_of_replicas": 1, 
    "analysis": {
      "analyzer": {
        "korean_mixed_analyzer": {
          "type": "custom", 
          "tokenizer": "nori_mixed", 
          "filter": []
        }
      }, 
      "tokenizer": {
        "nori_mixed": {
          "type": "nori_tokenizer", 
          "decompound_mode": "mixed"
        }
      }
    }
  }, 
  "mappings": {
    "properties": {
      "shop_id": {
        "type": "keyword"
      }, 
      "shop_name": {
        "type": "text", 
        "analyzer": "korean_mixed_analyzer"
      }, 
      "location": {
        "type": "geo_point"
      }, 
      "status": {
        "type": "keyword"
      }, 
      "category": {
        "type": "keyword"
      }, 
      "detail_category": {
        "type": "keyword"
      }, 
      "average_score": {
        "type": "double"
      }, 
      "delivery_tip_per_distance_list": {
        "properties": {
          "price": {
            "type": "integer"
          }, 
          "distance": {
            "type": "double"
          }
        }
      }
    }
  }
}
~~~