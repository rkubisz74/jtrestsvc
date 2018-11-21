1.Lista wpisów:
GET https://apps.rk-dev.pl/jtrestsvc/app
przyk³ad:
curl --header "Content-Type: application/json" --request GET --insecure https://apps.rk-dev.pl/jtrestsvc/app
dodatkowe opcjonalne parametry:
from=yyyy-MM-ddTHH:mm:ss
to=yyyy-MM-ddTHH:mm:ss
przyk³ad:
curl --header "Content-Type: application/json" --request GET --insecure "https://apps.rk-dev.pl/jtrestsvc/app?from=2018-06-18T08:00:00&to=2018-07-18&08:00:00"

2.Wpis na podstawie ID:
GET https://apps.rk-dev.pl/jtrestsvc/app/{id}
przyk³ad:
curl --header "Content-Type: application/json" --request GET --insecure https://apps.rk-dev.pl/jtrestsvc/app/1

3.dodanie wpisu: wartoœæ id z json'a je¿eli podana jest ignorowana
POST https://apps.rk-dev.pl/jtrestsvc/app
przyk³ad:
curl --header "Content-Type: application/json" --request POST --data "{\"value\":123,\"deviceId\":2,\"eventDate\":\"2018-09-01T08:00:00+02:00\"}" --insecure https://apps.rk-dev.pl/jtrestsvc/app

4.aktualizacja wpisu na podstawie ID: wartoœæ date z json'a je¿eli podana jest ignorowana
PUT https://apps.rk-dev.pl/jtrestsvc/app
przyk³ad:
curl --header "Content-Type: application/json" --request PUT --data "{\"id\":2,\"value\":9999}" --insecure https://apps.rk-dev.pl/jtrestsvc/app

5.usuwanie wpisu na podstawie ID:
DELETE https://apps.rk-dev.pl/jtrestsvc/app/{id}
przyk³ad:
curl --header "Content-Type: application/json" --request DELETE --insecure https://apps.rk-dev.pl/jtrestsvc/app/2

struktura json'a
{
  "id": long
  "date": Timestamp
  "value": float
  "deviceId": int
  "eventDate": Timestamp
}