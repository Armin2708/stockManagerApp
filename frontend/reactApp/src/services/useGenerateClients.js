import {useState} from "react";
import axios from "axios";

export function useGenerateClients(){
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState("");
        const generateClients = async () => {
            try{
                setIsLoading(true);
                setError("");
                const res = await axios
                    .post(`http://localhost:8010/insuranceApp/generateClient`)

                console.log(res)
                setError("")
            }catch (err){
                console.log(err.message);
                setError(err.message)
            }finally {
                setIsLoading(false)
            }
        }
    return{isLoading,error, generateClients}
}