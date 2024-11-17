import {useEffect, useState} from "react";
import axios from "axios";

export function useClients(firstName, lastName,pageNumber){
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState("");
    const [clients,setClients] = useState([]);
    const [maxPageNumber,setMaxPageNumber] = useState(0);

    useEffect(function () {

        const controller = new AbortController();
        async function fetchClients() {
            try{
                setIsLoading(true);
                setError("");
                const res = await axios.get(
                    `http://localhost:8010/insuranceApp/search/${firstName}&${lastName}?pageNumber=${pageNumber-1}`,
                    {signal: controller.signal}
                );
                const data = await res.data;

                setClients(data)
                //setMaxPageNumber(maxPage);

                setError("")
            }catch (err){
                console.log(err.message);

                if (err.name !== "AbortError") {
                    setError(err.message)
                }
            }finally {
                setIsLoading(false)
            }
        }
        fetchClients();

        return function (){
            controller.abort();
        }
    }, [firstName,lastName,pageNumber,setClients])

    return{clients,isLoading,error,maxPageNumber}
}