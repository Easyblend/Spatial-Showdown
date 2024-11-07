/* eslint-disable react/no-unescaped-entities */
import React, { useEffect, useRef } from 'react';
import { gsap } from 'gsap';
import { Stack, Typography } from '@mui/material';

function Technology({ page, setPage }) {
  const maskRef = useRef(null);

  return (
    <Stack>
      <Typography variant="h3" gutterBottom>
        Technology
      </Typography>
      <Typography
        variant="body1"
        gutterBottom
        ref={maskRef}
      >
        Fuck
      </Typography>
    </Stack>
  );
}

export default Technology;
