import {useEffect, useState} from 'react'
import './App.css'
import {Box} from '@chakra-ui/react'

import SearchBar from "./components/shared/SearchBar.jsx";
import {useClients} from "./services/useClients.js";
import ClientList from "./components/ClientList.jsx";
import UploadFile from "./components/UploadFile.jsx";
import AddressSelector from "./components/AddressSelector.jsx";
import PdfFileCreator from "./components/PdfFileCreator.jsx";

const App = () => {
  const [lastName,setLastName] = useState("");
  const [firstName,setFirstName] = useState("");
  const [pageNumber,setPageNumber] = useState(1);

  const {clients,isLoading,error,maxPageNumber} = useClients(firstName, lastName, pageNumber);

  const handleClearSearch = ()=>{
      setFirstName("");
      setLastName("");
  }
  const handleSearch = (firstName, lastName)=>{
      setFirstName(firstName);
      setLastName(lastName);
  }

  return (
      <Box>
          <SearchBar
              firstName={firstName}
              lastName={lastName}
              handleClearSearch={handleClearSearch}
              handleSearch={handleSearch}
              isLoading={isLoading}
          />
          <Box mb={4} />
          <ClientList
              clients={clients}
              isLoading={isLoading}
              error={error}
              pageNumber={pageNumber}
              setPageNumber={setPageNumber}
              maxPageNumber={maxPageNumber}
          />
      </Box>
  )
}

export default App;
