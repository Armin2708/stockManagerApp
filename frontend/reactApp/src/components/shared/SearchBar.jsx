import React, {useRef, useState} from "react";
import {useKey} from "../../services/useKey.js";
import {Box, Button, IconButton, Input} from "@chakra-ui/react";
import {SearchIcon} from "@chakra-ui/icons";

export default function SearchBar({firstName,lastName,handleClearSearch, handleSearch,isLoading}){

    const inputEl = useRef(null);
    const [propFirstName,setPropFirstName]=useState(firstName);
    const [propLastName,setPropLastName]=useState(lastName);

    const onClearSearch = () =>{
        setPropFirstName("");
        setPropLastName("");
        handleClearSearch();
    }

    return (
        <Box borderWidth='2px' borderRadius='lg' overflow='hidden' display={"flex"}>
            <Input
                variant={"unstyled"}
                className="search"
                type="text"
                placeholder="First Name"
                value={propFirstName}
                onChange={(e) => setPropFirstName(e.target.value)}
                ref={inputEl}
                isDisabled={isLoading}
            />
            <Input
                variant={"unstyled"}
                className="search"
                type="text"
                placeholder="Last Name"
                value={propLastName}
                onChange={(e) => setPropLastName(e.target.value)}
                ref={inputEl}
                isDisabled={isLoading}
            />
            <IconButton
                aria-label='Search database'
                icon={<SearchIcon />}
                onClick={() => handleSearch(propFirstName,propLastName)}
                isLoading={isLoading}
            />
            <Button
                onClick={() => onClearSearch()}
                isDisabled={isLoading}
            >
                Clear
            </Button>
        </Box>
    )
}